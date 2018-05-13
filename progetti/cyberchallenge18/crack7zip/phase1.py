import angr
import pdb
import resource
import sys
import claripy
import time

proj = angr.Project('access_denied', load_options={'auto_load_libs': False}) 

start = 0x080487EC
avoid = [0x08048859]
end = 0x08048851

state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

bind_addr = 0x0
arg = state.se.BVS("input", 8)
# state.memory.store(bind_addr, arg)
state.memory.store(state.regs.ebp + 0x8, arg)

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
            # print "Solution" + " = " + str(state.se.eval(arg, cast_to=str))
            print "Solution" + " = " + str(state.se.eval_upto(arg, 5))
        # break

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)

