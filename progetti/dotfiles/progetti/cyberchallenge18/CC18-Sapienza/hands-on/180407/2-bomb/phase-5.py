import angr, logging
import claripy
import pdb
import resource
import time

proj = angr.Project('bomb', load_options={'auto_load_libs' : False})

start = 0x401062
avoid = [0x40143a]
end = [0x4010ee]

# initial state is at the beginning of phase_one()
state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

arg = state.se.BVS("input_string", 8 * 128)
for o in arg.chop(8): #ensure that the character has to be readable
    state.se.add(state.se.Or(state.se.And(o >= 0x20, o <= 0x7e), o == 0))

# read_line() reads a line from stdin and stores it a this address
bind_addr = 0x603780

# bind the symbolic string at this address
state.memory.store(bind_addr, arg)
state.regs.rdi = bind_addr

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
        print "Solution: " + str(state.se.eval(arg, cast_to=str))
        break

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)
