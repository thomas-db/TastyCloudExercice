package com.tasty.thomas.tastycloudexercice.Model

import com.tasty.thomas.tastycloudexercice.Interface.IProduct

class Drink(override var name: String, override var price: Float, override var image: String, override var description: String, override var amount: Int, override var id: Int) : IProduct {
    init {

    }

    /*
    override var name: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var price: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var image: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var description: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var nbRemaining: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
        */
}