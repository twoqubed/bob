package com.twoqubed.bob.processor.generated;

public class FooNoCopyBuilder {

    private java.lang.String bar;
    private java.lang.String baz;
    private java.lang.Boolean qux;
    private boolean norf;

    public static FooNoCopyBuilder builder() {
        return new FooNoCopyBuilder();
    }

    public FooNoCopyBuilder withBar(java.lang.String bar) {
        this.bar = bar;
        return this;
    }

    public FooNoCopyBuilder withBaz(java.lang.String baz) {
        this.baz = baz;
        return this;
    }

    public FooNoCopyBuilder withQux(java.lang.Boolean qux) {
        this.qux = qux;
        return this;
    }

    public FooNoCopyBuilder withNorf(boolean norf) {
        this.norf = norf;
        return this;
    }

    public FooNoCopy build() {
        return new FooNoCopy(
                bar,
                baz,
                qux,
                norf
        );
    }
}
