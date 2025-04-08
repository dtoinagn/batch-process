package com.tiandn.batch;

/***
 * * @author tiandn
 * @date 2023/10/8
 * @description
 * 1. This is a record class that represents a person with a first name and a last name.
 * 2. The class is immutable, meaning that once an instance is created, its state cannot be changed.
 * 3. The fields are private and final, ensuring encapsulation and immutability.
 * 4. The class automatically generates a constructor, getters, equals, hashCode, and toString methods.
 */
public record Person(String first_name, String last_name) {
}

