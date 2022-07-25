/* XMRig
 * Copyright 2010      Jeff Garzik <jgarzik@pobox.com>
 * Copyright 2012-2014 pooler      <pooler@litecoinpool.org>
 * Copyright 2014      Lucas Jones <https://github.com/lucasjones>
 * Copyright 2014-2016 Wolf9466    <https://github.com/OhGodAPet>
 * Copyright 2016      Jay D Dee   <jayddee246@gmail.com>
 * Copyright 2017-2018 XMR-Stak    <https://github.com/fireice-uk>, <https://github.com/psychocrypt>
 * Copyright 2018-2020 SChernykh   <https://github.com/SChernykh>
 * Copyright 2016-2020 XMRig       <https://github.com/xmrig>, <support@xmrig.com>
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

#ifndef XMRIG_OCLTHREADS_H
#define XMRIG_OCLTHREADS_H


#include <vector>


#include "backend/opencl/OclThread.h"
#include "backend/opencl/wrappers/OclDevice.h"


namespace xmrig {


class OclThreads
{
public:
    OclThreads() = default;
    OclThreads(const rapidjson::Value &value);
    OclThreads(const std::vector<OclDevice> &devices, const Algorithm &algorithm);

    inline bool isEmpty() const                             { return m_data.empty(); }
    inline const std::vector<OclThread> &data() const       { return m_data; }
    inline size_t count() const                             { return m_data.size(); }
    inline void add(OclThread &&thread)                     { m_data.push_back(thread); }
    inline void reserve(size_t capacity)                    { m_data.reserve(capacity); }

    inline bool operator!=(const OclThreads &other) const   { return !isEqual(other); }
    inline bool operator==(const OclThreads &other) const   { return isEqual(other); }

    bool isEqual(const OclThreads &other) const;
    rapidjson::Value toJSON(rapidjson::Document &doc) const;

private:
    std::vector<OclThread> m_data;
};


} /* namespace xmrig */


#endif /* XMRIG_OCLTHREADS_H */
