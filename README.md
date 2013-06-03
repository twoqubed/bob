# Bob - A Builder of Builders

An Java annotation processors that generates builders.

## Usage

To use the annotation processor, simply include this library in your class path and any class annotated with
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

Builders are useful for helping create objects whose constructors contain several parameters. For example, consider
the `Person` example above. You would create a `Person` instance using the constructor like this:

    Person joe = new Person("Joe", "Blow", 35);

You would create an equivilant instance with a builder like this:

    Person joe = PersonBuilder.builder
            .withFirstName("Joe")
            .withLastName("Blow")
            .withAge(35)
            .build();

### Clarity

Now, with only three constructor parameters in this example, the builder is not providing much value. But suppose
we add four more constructor parameters: `int height`, `int weight`, `boolean married`, and `boolean employed`. Now
the constructor invocation looks like this.

    Person joe = new Person("Joe", "Blow", 35, 61, 185, true, false);

Looking at this, it is not clear which `int` and `boolean` arguments go to which parameter. However, it is explicit
when using a builder:

    Person joe = PersonBuilder.builder
            ...
            .withHeight(61)
            .withAge(185)
            .withMarried(true)
            .withEmployed(false)
            .build();

### Immutability and Consistency

The `withXxx(Type value)` method signature looks very similar to a typical _setter_ method. However, there are two
issues to consider when using setters.

First, by exposing setter methods, you objects are no longer immutable. When possible, you should favor immutability.

Second, by setting these values one by one via setter method, there is an opportunity to create an object in an invalid
state. For example, this will create a `Person` in an invalid state.

    Person invalid = new Person();
    invalid.setFirstName("Joe");

We now have an object in an invalid state with no way to programtically defend against it. On the other hand, consider
equivilant scenario using a builder:

    Person joe = PersonBuilder.builder
            .withFirstName("Joe")
            .build();

At this point, the constructor that is invoked by the builder has the oppotunity to inspect the state of the object and
throw an exception if it is not constructed in a valid state. <sup>[1](#footnotes)</sup>

    public Person(String firstName, String lastName, ...) {
        if (firstName == null) {
            throw IllegalArgumentException("First name cannot be null");
        }
        this.firstName = firstName;
        ...
    }

In this case, we can programtically guarantee a `Person` will always be constructed in an invalid state and will be
immutable from that point forward.

### Prototypes

An added benefit of using builders is that they make for great prototypes, particularly for testing. For example, suppose
you need an instance of a `Person` who is married and employed for several test cases. You could create a helper class
for just this purpose:

    public class PersonPrototypes { // or maybe PersonFixtures

        public static PersonBuilder aMarriedAndEmployedPerson() {
            return new PersonBuilder()
                    ...
                    ...
                    .withMarried(true)
                    .withEmployed(true);
        }
    }

You now have way to easlily create instances of a `Person` in the desired married and employed state:

    Person person = PersonPrototypes.aMarriedAndEmployedPerson().build();

And since the new builder is created each time, you can safely customize the builder without tainting any other tests:

    Person person = PersonPrototypes
            .aMarriedAndEmployedPerson()
            .withAge(65)
            .build();

## Acknowledgements

Thanks to [Paul Holser](https://github.com/pholser) and [Todd Stout](https://github.com/tstout) for their
help and inspiration on this project.

Thanks to [Jorge Hidalgo](http://deors.wordpress.com/) for helping me get the ball rolling with his wonderful
[tutorial](http://deors.wordpress.com/2011/09/26/annotation-types/) on annotation processors.

## Footnotes

1. Explicitly checking each parameter in an if block and conditionally throwing an exception can get quite noisy.
Consider usint Guava's [`Preconditions`](http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Preconditions.html)
instead.
