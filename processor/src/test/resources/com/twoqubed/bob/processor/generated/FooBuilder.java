package com.twoqubed.bob.processor.generated;

public class FooBuilder {

    private java.lang.String bar;
    private java.lang.String baz;

    public static FooBuilder builder() {
        return new FooBuilder();
    }

    public FooBuilder withBar(java.lang.String bar) {
        this.bar = bar;
        return this;
    }

    public FooBuilder withBaz(java.lang.String baz) {
        this.baz = baz;
        return this;
    }

    public Foo build() {
        return new Foo(
                bar,
                baz
        );
    }
}
