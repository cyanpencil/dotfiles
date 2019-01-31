import angr
import claripy
import pdb

# create a angr project
proj = angr.Project('../2-bomb/bomb', load_options={'auto_load_libs' : False})
print proj

# start from entry state
state = proj.factory.entry_state(remove_options={angr.options.LAZY_SOLVES,}) # add_options={angr.options.SYMBOLIC_WRITE_ADDRESSES}
print state

# start from an arbitrary address
#start = 0xABCD
#state = proj.factory.blank_state(addr=start, remove_options={angr.options.LAZY_SOLVES,})

# create a concrete object
obj_concrete = state.se.BVV(0xABCD, 32) # 32 bits
print obj_concrete

# state.se is a wrapper arround claripy stuff. Hence, this is the same thing:
obj_concrete = claripy.BVV(0xABCD, 32) # 32 bits
print obj_concrete
print len(obj_concrete) # number of bits

# symbolic object
obj_symbolic = state.se.BVS('choose_a_label', 32) # 32 bits
print obj_symbolic

# add constraints over a symbolic object: e.g., 0 < obj <= 10
state.se.add(obj_symbolic > 0)
state.se.add(obj_symbolic <= 10)
print state.se.constraints # path constraints

# construct expressions
expr_1 = obj_symbolic + 0x10
print expr_1

# use concat operator to build complex objects composed by sub-objects
expr_2 = state.se.Concat(expr_1, obj_concrete) # this it now 64 bits
print expr_2
print len(expr_2)

state.se.add(expr_2 > 0xABCD)
print state.se.eval_upto(expr_2, 10)

# conditional expression
expr_3 = state.se.If(obj_symbolic == 0x7, state.se.BVV(0x0, 32), state.se.BVV(0x1, 32))
print state.se.eval_upto(expr_3, 2)
print state.se.eval_upto(expr_3, 2, extra_constraints=[obj_symbolic < 5])

# see other stuff: https://docs.angr.io/docs/claripy.html

# read/write registers

print state.regs.eax
state.regs.eax = 0x10
print state.regs.eax
state.regs.eax = obj_concrete
print state.regs.eax
state.regs.eax = obj_symbolic
print state.regs.eax

print state.regs.rsp
print state.se.symbolic(state.regs.rsp)
print state.regs.rbp # not constant at the beginning
print state.se.symbolic(state.regs.rbp)

# read/write memory

data = state.memory.load(0xABCD, 4) # reading 4 bytes at 0xABCD
print data				# big endian
print data.reversed		# little endian
data = state.memory.load(0xABCD, 4, endness='Iend_LE')
print data
state.memory.store(0xABCD, state.se.BVV(0x1, 32), 4, endness='Iend_LE')
print state.memory.load(0xABCD, 4, endness='Iend_LE')
state.memory.store(0xABCD, state.se.BVV(0x1, 32).reversed, 4)
print state.memory.load(0xABCD, 4, endness='Iend_LE')

# stack
state.stack_push(0xA) 	# 64 bytes on x86_64
print state.stack_pop() # 64 bytes on x86_64
state.memory.store(state.regs.rsp, 0xA, 4, endness='Iend_LE') # same as stack_push()
print state.stack_pop() # 64 bytes on x86_64

# patching bytes

def foo(state):
	# do stuff on state
	pass

proj.hook(0xABCDE, foo, length=5) 	# execute foo when %eip is 0xABCDE
								# %eip is incremented by length
								# if length is omitted, %eip is not incremented

# creating a new API model

class NotVeryRand(angr.SimProcedure):
    def run(self):
        if 'rand_idx' in self.state.procedure_data.global_variables:
            rand_idx = self.state.procedure_data.global_variables['rand_idx']
        else:
            rand_idx = 0
 
        out = rand_idx
        self.state.procedure_data.global_variables['rand_idx'] = rand_idx + 1
        return out

proj.hook_symbol('rand', NotVeryRand) # rand is not used by bomb... it will fail

# imports from the main binary
print proj.loader.main_object.imports.keys()

