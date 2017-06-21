	.data
root: .word 7, n01, n02
n01: .word -4, n03, n04
n02: .word 5, n05, n06
n03: .word 5, n07, n08
n04: .word 2, 0, n09
n05: .word 13, 0, 0
n06: .word -15, n10, n11
n07: .word 1, 0, 0
n08: .word 0, n12, n13
n09: .word 27, 0, 0
n10: .word 23, n14, 0
n11: .word 31, 0, n15
n12: .word -8, 0, 0
n13: .word 12, 0, 0
n14: .word 2, 0, 0
n15: .word 2, 0, 0
	
	.text
	la $a0, root
	li $a1, 0
	jal visit
	move $t0, $v0
	li $v0, 1
	la $a0, ($t0)
	syscall
	j end
	
	
visit:
	bne $a0, $zero, skip
	move $v0, $a1
	jr $ra
skip:

	#t0 è il valore del nodo corrente; t1 è il figlio sinistro; t2 il figlio destro; t3 la somma fino a adesso;
	# t4 è il risultato del figlio sinistro; t5 il risultato del figlio destro

	addi $sp, $sp, -16
	sw $t2, 0($sp)
	sw $t3, 4($sp)
	sw $t4, 8($sp)
	sw $ra, 12($sp)
	
	#a0 è il nodo corrente; a1 la somma fino a adesso
	lw $t0, 0($a0)
	lw $t1, 4($a0)
	lw $t2, 8($a0)
	move $t3, $a1
	
	add $t3, $t3, $t0

	#visito il figlio sinistro	
	addi $a0, $t1, 0
	addi $a1, $t3, 0
	jal visit
	addi $t4, $v0, 0
	
	#visito il figlio destro
	addi $a0, $t2, 0
	addi $a1, $t3, 0
	jal visit
	
	#salvo in v0 il massimo tra sottoalbero sinistro e destro
	bgt $v0, $t4, us
	move $v0, $t4
	
	#rimetto a posto i registri usati
us:   lw $t2, 0($sp)
	lw $t3, 4($sp)
	lw $t4, 8($sp)
	lw $ra, 12($sp)
	addi $sp, $sp, 16
	jr $ra
	
end:
