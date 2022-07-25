/* XMRig
 * Copyright 2010      Jeff Garzik <jgarzik@pobox.com>
 * Copyright 2012-2014 pooler      <pooler@litecoinpool.org>
 * Copyright 2014      Lucas Jones <https://github.com/lucasjones>
 * Copyright 2014-2016 Wolf9466    <https://github.com/OhGodAPet>
 * Copyright 2016      Jay D Dee   <jayddee246@gmail.com>
 * Copyright 2017-2018 XMR-Stak    <https://github.com/fireice-uk>, <https://github.com/psychocrypt>
 * Copyright 2018-2019 SChernykh   <https://github.com/SChernykh>
 * Copyright 2016-2019 XMRig       <https://github.com/xmrig>, <support@xmrig.com>
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


#include "backend/opencl/kernels/rx/FindSharesKernel.h"
#include "backend/opencl/wrappers/OclLib.h"


void xmrig::FindSharesKernel::enqueue(cl_command_queue queue, size_t threads)
{
    const size_t gthreads        = threads;
    static const size_t lthreads = 64;

    enqueueNDRange(queue, 1, nullptr, &gthreads, &lthreads);
}


// __kernel void find_shares(__global const uint64_t* hashes, uint64_t target, uint32_t start_nonce, __global uint32_t* shares)
void xmrig::FindSharesKernel::setArgs(cl_mem hashes, cl_mem shares)
{
    setArg(0, sizeof(cl_mem), &hashes);
    setArg(3, sizeof(cl_mem), &shares);
}


void xmrig::FindSharesKernel::setTarget(uint64_t target)
{
    setArg(1, sizeof(uint64_t), &target);
}


void xmrig::FindSharesKernel::setNonce(uint32_t nonce)
{
    setArg(2, sizeof(uint32_t), &nonce);
}
