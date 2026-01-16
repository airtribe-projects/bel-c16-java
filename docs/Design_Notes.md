# Design Notes

# 1. Encapsulation

I used 'private' fields in the 'Person' and 'Student' classes. This ensures that data can only be accessed or modified through public 'getter' and 'setter' methods, protecting the integrity of the data.

# 2. Inheritance

The 'Student' class extends the 'Person' class. This allows the student to inherit common attributes like `firstName` and `lastName`, reducing code duplication.

# 3. Static Members

In the 'IdGenerator' class, I used a 'static' counter. This ensures that the ID state is shared across the entire application, so every new student gets a unique, incremental ID regardless of where they are created.

# 4. Collection Framework

I chose 'ArrayList' over a standard Array because the number of students is dynamic. 'ArrayList' provides built-in methods for adding and listing items without needing to manually resize the storage.