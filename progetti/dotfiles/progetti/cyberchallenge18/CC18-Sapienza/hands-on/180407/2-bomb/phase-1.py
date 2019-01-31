import angr, logging
import claripy
import pdb
import resource
import time

proj = angr.Project('bomb', load_options={'auto_load_libs' : False})

start = 0x400ee0
avoid = [0x40143a]
end = [0x400ef7]

# initial state is at the beginning of phase_one()
state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

# a symbolic input string with a length up to 128 bytes
arg = state.se.BVS("input_string", 8 * 128)
# an ending byte
arg_end = state.se.BVS("end_input_string", 8)
# add a constraint on this byte to force it to be '\0'
# the constraint is added to the state.
# Another way to do same is with:
#   arg_end = state.se.BVV(0x0, 8)
# in this case arg_end is a concrete value
state.se.add(arg_end == 0x0)
# concat arg and arg_end
arg = state.se.Concat(arg, arg_end)

# an address where to store my arg
bind_addr = 0x603780

# bind the symbolic string at this address
state.memory.store(bind_addr, arg)

# phase_one reads the string [rdi]
state.regs.rdi = bind_addr

# make rsi concrete
state.regs.rsi = 0x0

pg = proj.factory.simulation_manager(state, veritesting=False)
#pg = proj.factory.simulation_manager(state, veritesting=True, veritesting_options={'boundaries': end + avoid})

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
        print "Solution: " + state.se.eval(arg, cast_to=str)
        break

print
print "Memory usage: " + str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024) + " MB"
print "Elapsed time: " + str(time.time() - start_time)
