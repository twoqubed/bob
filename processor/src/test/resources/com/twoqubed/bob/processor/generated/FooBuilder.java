package com.twoqubed.bob.processor.generated;

public class FooBuilder {

    private java.lang.String bar;
    private java.lang.String baz;
    private java.lang.Boolean qux;
    private boolean norf;
    private java.util.List<java.lang.String> list = new java.util.ArrayList<java.lang.String>();
    private java.util.Set<java.lang.String> set = new java.util.HashSet<java.lang.String>();
    private java.util.Map<java.lang.String, java.lang.Integer> map = new java.util.HashMap<java.lang.String, java.lang.Integer>();
    private int[] intArray = new int[0];
    private java.lang.Object[] objectArray = new java.lang.Object[0];

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

    public FooBuilder withQux(java.lang.Boolean qux) {
        this.qux = qux;
        return this;
    }

    public FooBuilder withNorf(boolean norf) {
        this.norf = norf;
        return this;
    }

    public FooBuilder withList(java.util.List<java.lang.String> list) {
        this.list = list;
        return this;
    }

    public FooBuilder withSet(java.util.Set<java.lang.String> set) {
        this.set = set;
        return this;
    }

    public FooBuilder withMap(java.util.Map<java.lang.String, java.lang.Integer> map) {
        this.map = map;
        return this;
    }

    public FooBuilder withIntArray(int[] intArray) {
        this.intArray = intArray;
        return this;
    }

    public FooBuilder withObjectArray(java.lang.Object[] objectArray) {
        this.objectArray = objectArray;
        return this;
    }

    public Foo build() {
        return new Foo(
                bar,
                baz,
                qux,
                norf,
                list,
                set,
                map,
                intArray,
                objectArray
        );
    }
}