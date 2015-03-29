# Lattice

* Totally not in any usable state yet, go look somewhere else. *

## Mission statement

Scala library to easily manipulate generic 2-dimensional lattices (essentially, non-numeric matrices).

The goal is to make it painless to work with rectangular 2d arrays with reasonably efficient immutable operations. 
This is useful for things like cellular automata and game engines, I guess.

For linear algebra, you might want to look at [Breeze](https://github.com/scalanlp/breeze) instead. 
In Lattice, there are no operations to deal with numbers specifically. Typically a lattice will be filled with your own objects. 
The available higher order functions should make it easy enough to implement your own numerical functions, though.

## Concepts

Lattices are always rectangular.