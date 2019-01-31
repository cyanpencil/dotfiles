import angr
import pdb
import resource
import sys
import claripy
import time

proj = angr.Project('ais3_crackme', load_options={'auto_load_libs': False}) 

start = 0x004005f6 
avoid = [0x0040060e]
end = 0x00400602

state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

bind_addr = 0x0
arg = state.se.BVS("input_string", 8*128)
state.memory.store(bind_addr, arg)
state.regs.rax = bind_addr

sm = proj.factory.simulation_manager(state, veritesting=False)

start_time = time.time()
while len(sm.active) > 0:
    print sm
    sm.explore(avoid=avoid, find=end, n=1)

    if len(sm.found) > 0:
        print
        print "Reached the target"
        print sm
        for s in sm.found:
            state = s.state
            print "Solution" + " = " + str(state.se.eval(arg, cast_to=str))
        # break

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)

