/* XMRig
 * Copyright (c) 2018-2021 SChernykh   <https://github.com/SChernykh>
 * Copyright (c) 2016-2021 XMRig       <https://github.com/xmrig>, <support@xmrig.com>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

#include <algorithm>


#include "backend/cpu/CpuThreads.h"
#include "3rdparty/rapidjson/document.h"
#include "base/io/json/Json.h"


namespace xmrig {


static const char *kAffinity    = "affinity";
static const char *kIntensity   = "intensity";
static const char *kThreads     = "threads";


static inline int64_t getAffinityMask(const rapidjson::Value &value)
{
    if (value.IsInt64()) {
        return value.GetInt64();
    }

    if (value.IsString()) {
        const char *arg = value.GetString();
        const char *p   = strstr(arg, "0x");

        return p ? strtoll(p, nullptr, 16) : strtoll(arg, nullptr, 10);
    }

    return -1L;
}


static inline int64_t getAffinity(uint64_t index, int64_t affinity)
{
    if (affinity == -1L) {
        return -1L;
    }

    size_t idx = 0;

    for (size_t i = 0; i < 64; i++) {
        if (!(static_cast<uint64_t>(affinity) & (1ULL << i))) {
            continue;
        }

        if (idx == index) {
            return static_cast<int64_t>(i);
        }

        idx++;
    }

    return -1L;
}


} // namespace xmrig


xmrig::CpuThreads::CpuThreads(const rapidjson::Value &value)
{
    if (value.IsArray()) {
        for (auto &v : value.GetArray()) {
            CpuThread thread(v);
            if (thread.isValid()) {
                add(thread);
            }
        }
    }
    else if (value.IsObject()) {
        uint32_t intensity   = Json::getUint(value, kIntensity, 1);
        const size_t threads = std::min<unsigned>(Json::getUint(value, kThreads), 1024);
        m_affinity           = getAffinityMask(Json::getValue(value, kAffinity));
        m_format             = ObjectFormat;

        if (intensity < 1 || intensity > 5) {
            intensity = 1;
        }

        for (size_t i = 0; i < threads; ++i) {
            add(getAffinity(i, m_affinity), intensity);
        }
    }
}


xmrig::CpuThreads::CpuThreads(size_t count, uint32_t intensity)
{
    m_data.reserve(count);

    for (size_t i = 0; i < count; ++i) {
        add(-1, intensity);
    }
}


bool xmrig::CpuThreads::isEqual(const CpuThreads &other) const
{
    if (isEmpty() && other.isEmpty()) {
        return true;
    }

    return count() == other.count() && std::equal(m_data.begin(), m_data.end(), other.m_data.begin());
}


rapidjson::Value xmrig::CpuThreads::toJSON(rapidjson::Document &doc) const
{
    using namespace rapidjson;
    auto &allocator = doc.GetAllocator();

    Value out;

    if (m_format == ArrayFormat) {
        out.SetArray();

        for (const CpuThread &thread : m_data) {
            out.PushBack(thread.toJSON(doc), allocator);
        }
    }
    else {
        out.SetObject();

        out.AddMember(StringRef(kIntensity), m_data.empty() ? 1 : m_data.front().intensity(), allocator);
        out.AddMember(StringRef(kThreads), static_cast<unsigned>(m_data.size()), allocator);
        out.AddMember(StringRef(kAffinity), m_affinity, allocator);
    }

    return out;
}
