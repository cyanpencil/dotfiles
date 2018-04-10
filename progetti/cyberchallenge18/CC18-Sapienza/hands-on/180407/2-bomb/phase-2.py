import angr, logging
import claripy
import pdb
import resource
import time

proj = angr.Project('bomb', load_options={'auto_load_libs' : False})

start = 0x400f0a    # after calling read_six_numbers()
avoid = [0x40143a]
end = [0x400f3c]

# initial state is at the beginning of phase_one()
state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

# push six numbers into the stack
# as done by read_six_numbers
# since this is a x86_64 binary:
# each push into the stack works as a pair of ints 
N = []
for i in xrange(3):
    a = state.se.BVS('int{}'.format(i * 2), 32)
    b = state.se.BVS('int{}'.format(i * 2 + 1), 32)
    N.append(a)
    N.append(b)
    c = state.se.Concat(a, b)
    state.stack_push(c)

pg = proj.factory.simulation_manager(state, veritesting=False)
#pg = proj.factory.path_group(state, veritesting=True, veritesting_options={'boundaries': end + avoid})

start_time = time.time()
while len(pg.active) > 0:

    print pg

    # step 1 basic block for each active path
    # if veritesting is on: this will step more than one 1 BB!
    pg.explore(avoid=avoid, find=end, n=1)

    # Bazinga!
    if len(pg.found) > 0:
        print
    	print "Reached the target"
        print pg
        state = pg.found[0].state
	    #print state.se.constraints
        for i, n in enumerate(reversed(N)):
            print "n[" + str(i) + "]: " + str(state.se.eval_upto(n, 2))
        break

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)
