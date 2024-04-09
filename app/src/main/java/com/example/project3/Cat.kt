package com.example.project3

/*
    Cat class contains:
        name : String
        temperament : String
        origin : String
        description : String
        imageUrl : String
    All of these are holding values from our json response from the API call, storing info on each cat
 */

class Cat(id:String, name: String, temperament: String, origin: String, description: String, imageUrl : String) {
    var id: String = id
        get() = field
        set(value) {
            field = value
        }
    var name: String = name
        get() = field
        set(value) {
            field = value
        }

    var temperament: String = temperament
        get() = field
        set(value) {
            field = value
        }

    var origin: String = origin
        get() = field
        set(value) {
            field = value
        }

    var description: String = description
        get() = field
        set(value) {
            field = value
        }

    // adding a way to store the image url from the json info

    var imageUrl : String = imageUrl
        get() = field
        set(value) {
            field = value
        }
}
