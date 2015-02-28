package com.twoqubed.bob.processor.generated;

public class FooWithCopyBuilder {

    private java.lang.String bar;
    private java.lang.String baz;
    private java.lang.Boolean qux;
    private boolean norf;

    public static FooWithCopyBuilder builder() {
        return new FooWithCopyBuilder();
    }

    public static FooWithCopyBuilder fromFooWithCopy(FooWithCopy fooWithCopy) {
        return new FooWithCopyBuilder()
                    .withBar(fooWithCopy.getBar())
                    .withBaz(fooWithCopy.getBaz())
                    .withQux(fooWithCopy.isQux())
                    .withNorf(fooWithCopy.isNorf());
    }

    public FooWithCopyBuilder withBar(java.lang.String bar) {
        this.bar = bar;
        return this;
    }

    public FooWithCopyBuilder withBaz(java.lang.String baz) {
        this.baz = baz;
        return this;
    }

    public FooWithCopyBuilder withQux(java.lang.Boolean qux) {
        this.qux = qux;
        return this;
    }

    public FooWithCopyBuilder withNorf(boolean norf) {
        this.norf = norf;
        return this;
    }

    public FooWithCopy build() {
        return new FooWithCopy(
                bar,
                baz,
                qux,
                norf
        );
    }
}
