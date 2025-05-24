# Turing-Machine-with-1-band

Turing Machine with 1 band - can accept 
or refuse words based on a grammar.
Can also say if a machine is deterministic or not.

Initialize tape with:

Left marker 'B'

The input word

Right marker 'B'

Start from position 0 and the initial state.

Loop:

Display the current state and tape.

Find the transition matching the current state and symbol under the head.

If no valid transition:

Accept if the state is final.

Otherwise, reject.

If a transition is found:

Write a new symbol on the tape.

Change state.

Move head left or right.

If moving right beyond tape length, extend the tape with 'B'.

If moving left beyond index 0, reject (head fell off tape).
