import angr
import pdb
import resource
import sys
import claripy
import time

proj = angr.Project('main',                 # path to the binary
    load_options={'auto_load_libs': False}) # avoid to load code related to shared libs

start = 0x400576            # addr of foobar
avoid = [0x4005d5]          # point(s) that are not interesting (e.g., early exits)
end = 0x400577              # point that I want to reach

# blank_state since exploration should start from an arbitrary point
# otherwise, use entry_state()
state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

# get reference to symbolic objects
# initially stored inside the registers
# (used for function parameters)
a = state.regs.edi
b = state.regs.esi

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

    # Bazinga!
    if len(sm.found) > 0:
        print
        print "Reached the target"
        print sm
        state = sm.found[0].state
        print "%edi = " + str(state.se.eval_upto(a, 100))
        print "%esi = " + str(state.se.eval_upto(b, 100))
        break

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)
