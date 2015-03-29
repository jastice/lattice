package object lattice {

  type Coord = (Int,Int)
  type Dim = (Int,Int)
  type Window = (Coord,Coord)


  /**
   * Lattices are rectangular 2d arrays which are expandable in any direction.
   * Coordinates are relative to an origin and can be negative.
   *
   * Space not initialized is considered to be filled with `zero` elements. This ensures that no operations will
   * throw bounds exceptions.
   *
   * @tparam T the element type the lattice is filled with.
   */
  trait Lattice[T] {

    /** The `zero` or default element. Uninitialized spaces use this value when there is space to fill. */
    val zero: T

    /** Dimensions of this lattice. */
    val dimensions: Dim

    /** Origin of the coordinates of this lattice relative to the "physical" origin. */
    val origin: Coord


    /** Append a single element sideways after the last column, to the first row of this lattice.
      * If this lattice has multiple rows, fill the additional space with default element. */
    def append(t: T): Lattice[T]

    def prepend(t: T): Lattice[T]

    /** Append a single element vertically after the last row, to the first column of this lattice.
      * If this lattice has multiple rows, fill the additional space with default element. */
    def appendVertical(t: T): Lattice[T]

    def prependVertical(t: T): Lattice[T]

    /** Concatenate a lattice sideways after the last column of this lattice.
      * If the lattices have an unequal number of rows, fill the additional space with default element. */
    def concat(other: Lattice[T]): Lattice[T]

    /** Concatenate a lattice vertically after the last row of this lattice.
      *  If the lattices have an unequal number of columns, fill the additional space with default element. */
    def concatVertical(other: Lattice[T]): Lattice[T]

    /** Update the value at given coordinates. If the coordinate is outside the lattice dimensions,
      * grow the lattice to accommodate it. */
    def update(at: Coord, t: T): Lattice[T]

    /** Update the values in a window with the values from `other`, starting at upper left corner. If `ts` is too small,
      * fill with zero element. If it is too large to fit in the window, ignore the extraneous values. */
    def updateWindow(window: Window, other: Lattice[T]): Lattice[T]

    /** A window slice of this lattice with the given window coordinates. */
    def window(window: Window): Lattice[T]

    /** A window slice of this lattice with given dimensions and centered on the given origin */
    def windowCentered(center: Coord, dims: Dim): Lattice[T]

    /** Set the origin of this lattice to given coordinates. If they are outside the dimensions of this lattice,
      * grow the new lattice appropriately. */
    def centered(center: Coord): Lattice[T]

    /** Transpose this lattice. */
    def transpose: Lattice[T]

    def map[U](f: T => U): Lattice[U]

    /** Map a function onto a window centered around each cell of this lattice. */
    def mapWindow[U](dims: Dim, f: Lattice[T] => U): Lattice[U]

    def fold(f: (T,T) => T): T

    def foldLeft[U](f: (U,T) => U): U

    /** Fold rows, top to bottom. */
    def foldRows[U](f: (Lattice[U], Lattice[T]) => Lattice[U]): Lattice[U]

    /** Fold columns, left to right. */
    def foldColumns[U](f: (Lattice[U], Lattice[T]) => Lattice[U]): Lattice[U]
  }

  implicit class LatticeSugar[T](lattice: Lattice[T]) {

    // operator aliases for lattices

  }

  object Lattice {

    def fill[A](rows: Int, cols: Int)(elem: => A):  Lattice[A] = ???

    def map2[T,U,R](a: Lattice[T], b: Lattice[U], f: (T,U)=>R ): Lattice[R] = ???

  }


}
