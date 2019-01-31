import angr, logging
import claripy
import pdb
import resource
import time

proj = angr.Project('bomb', load_options={'auto_load_libs' : False})

start = 0x40102e    # after calling sscanf
avoid = [0x40143a]
end = [0x40105d]

# initial state is at the beginning of phase_one()
state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

# push two numbers into the stack
N = [state.se.BVS('int{}'.format(0), 32), state.se.BVS('int{}'.format(1), 32)]
state.memory.store(state.regs.rsp + 0x8, N[0].reversed, 4)
state.memory.store(state.regs.rsp + 0xc, N[1].reversed, 4)

pg = proj.factory.simulation_manager(state, veritesting=False)
#pg = proj.factory.path_group(state, veritesting=True, veritesting_options={'boundaries': end + avoid})

start_time = time.time()
while len(pg.active) > 0:

    print pg

    # step 1 basic block for each active path
    # if veritesting is on: this will step more than one 1 BB!
    pg.explore(avoid=avoid, find=end, n=1)

if len(pg.found) > 0:
    print
    print "Reached the target"
    print pg
    print
    for k in range(len(pg.found)):
        print "Found state #" + str(k)
        state = pg.found[k].state
        for i, n in enumerate(N):
            print "n[" + str(i) + "]: " + str(state.se.eval_upto(n, 2))
        print

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)