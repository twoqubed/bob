# Bob - A Builder of Builders

An Java annotation processors that generates builders.

## Usage

To use the annotation processor, simply include this library in your class path and any class annotation with
`Built` will have it's builder generated. The builder will contain:

* A static `builder()` method that returns an instance of the builder class
* One `withXxx(Type value)` for each parameter in the target class' constructor. This method will have a single
parameter that is the same at the corresponding constructor parameters's type.
* A `build` method that will return the built instance of the target class.

For example, given the following class:

    @Built
    public Person {
        private final String firstName;
        private final String lastName;
        private final int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        ...
    }

The builder that is produced would look like this:

    public class PersonBuilder {

        private String firstName;
        private String lastName;
        private int age;

        public static PersonBuilder builder() {
            return new PersonBuilder();
        }

        public PersonBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(firstName, lastName, age);
        }
    }

The only constraints are:

* The target class must be annotated with the `Built` annotation
* The must be exactly one constructor that contains arguments. No-arg constructors are ignored. If multiple constructors
are found that take arguments, a compile error will be raised.

## Why Builders?

content to come

## Acknowledgements

Thanks to [Paul Holser](https://github.com/pholser) and [Todd Stout](https://github.com/tstout) for their
help and inspiration on this project.

Thanks to [Jorge Hidalgo](http://deors.wordpress.com/) for helping me get the ball rolling with his wonderful
[tutorial](http://deors.wordpress.com/2011/09/26/annotation-types/) on annotation processors.
