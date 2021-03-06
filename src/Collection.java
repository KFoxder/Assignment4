/**
 * Collection Interface
 * <p>
 * Note: DO NOT EDIT THIS FILE.
 *
 * ***********************************************************************
 * Computer Science 102: Data Structures
 * New York University, Fall 2013,
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
 * (c) Copyright 1995, 2013, Oracle Corporation and/or its affiliates (Oracle).
 * All rights reserved.
 * http://docs.oracle.com/javase/tutorial/information/license.html
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-09-01
 */

public interface Collection<E> { // extends Iterable<E> {
    /**
     * Basic collection operations
     */
    int size();
    boolean isEmpty();

    /**
     * Standard output operations
     */
    String toString();
    void PrettyPrint();
}