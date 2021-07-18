package com.github.juliherms.product.utils

/**
 * generic method for update element in the list
 */
fun <E> MutableSet<E>.update(element:E): Boolean {
    return this.remove(element) && this.add(element)
}