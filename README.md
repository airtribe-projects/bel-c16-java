# LearnTrack: Student Management System

### Project Overview
A Java-based system to manage student enrollment and course tracking using OOP principles.

### Class Diagram
[ Person ] <|-- [ Student ]
[ StudentService ] o-- [ Student ] : Manages (ArrayList)
[ StudentService ] --> [ Course ] : Enrolls
[ Main ] --> [ StudentService ] : Orchestrates
[ StudentService ] ..> [ EntityNotFoundException ] : Throws