import angr
import pdb
import resource
import sys
import claripy
import time

proj = angr.Project('bomb',                 # path to the binary
    load_options={'auto_load_libs': False}) # avoid to load code related to shared libs

start = 0x004010d2            # addr of foobar
avoid = [0x00401084, 0x004010c6]
end = 0x004010ee              # point that I want to reach

# blank_state since exploration should start from an arbitrary point
# otherwise, use entry_state()
state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

bind_addr = 0xabcd
state.regs.rdi = bind_addr
N = []
for i in range(6):
    N += [state.se.BVS("param"+str(i), 8)]
    # state.memory.store(state.regs.rsp + 0x18 + i, N[i])
    state.memory.store(bind_addr + i, N[i])
state.memory.store(bind_addr + 6, 0x0, 8)

# state.regs.rsi = bind_addr
# state.regs.rsp = bind_addr

# get reference to symbolic objects
# initially stored inside the registers
# (used for function parameters)
# a = state.regs.edi
# b = state.regs.esi

sm = proj.factory.simulation_manager(state,
    veritesting=False)  # veritesting is an implementation of state merging
                        # and it should be used when there are many paths
                        # since it could save a lot of memory
                        # Unfortunately, things get a little bit more
                        # complex when it is enabled. Let's disable it
                        # for this binary.

start_time = time.time()
while len(sm.active) > 0:
    print sm # get a feeling of what is happening
    # step 1 basic block for each active path 
    # if veritesting is on: this will step more than one 1 BB!
    sm.explore(avoid=avoid, find=end, n=1)


if len(sm.found) > 0:
    print
    print "Reached the target"
    print sm
    for s in sm.found:
        state = s.state
        for i in range(len(N)):
            print "Solution" + str(i) + " = " + str(state.se.eval(N[i]))
    # break

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)

