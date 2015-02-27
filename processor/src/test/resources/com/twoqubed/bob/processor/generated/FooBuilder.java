package com.twoqubed.bob.processor.generated;

public class FooBuilder {

    private java.lang.String bar;
    private java.lang.String baz;
    private java.lang.Boolean qux;
    private boolean norf;

    public static FooBuilder builder() {
        return new FooBuilder();
    }

    public static FooBuilder fromFoo(Foo foo) {
        return new FooBuilder()
                    .withBar(foo.getBar())
                    .withBaz(foo.getBaz())
                    .withQux(foo.isQux())
                    .withNorf(foo.isNorf());
    }

    public FooBuilder withBar(java.lang.String bar) {
        this.bar = bar;
        return this;
    }

    public FooBuilder withBaz(java.lang.String baz) {
        this.baz = baz;
        return this;
    }

    public FooBuilder withQux(java.lang.Boolean qux) {
        this.qux = qux;
        return this;
    }

    public FooBuilder withNorf(boolean norf) {
        this.norf = norf;
        return this;
    }

    public Foo build() {
        return new Foo(
                bar,
                baz,
                qux,
                norf
        );
    }
}
