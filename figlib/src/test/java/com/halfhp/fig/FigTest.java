package com.halfhp.fig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import java.lang.reflect.Method;
import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class FigTest {

    class A {
        int d = 0;
        private boolean aBooleanPrimitive = false;

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public boolean isaBooleanPrimitive() {
            return aBooleanPrimitive;
        }

        public void setaBooleanPrimitive(boolean aBooleanPrimitive) {
            this.aBooleanPrimitive = aBooleanPrimitive;
        }
    }

    class B {
        A a = new A();

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    class C {
        B b = new B();

        public B getB() {
            return b;
        }

        public void setB(B a) {
            this.b = b;
        }
    }

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }


    @Test
    public void testGetFieldAt() throws Exception {
        C c = new C();
        assertEquals(c, Fig.getObjectContaining(c, "b"));
        assertEquals(c.getB(), Fig.getObjectContaining(c, "b.a"));
        assertEquals(c.getB().getA(), Fig.getObjectContaining(c, "b.a.d"));
    }

    @Test
    public void testGetSetter() throws Exception {
        C c = new C();

        Method m = Fig.getSetter(c.getClass(), "b");
        assertEquals(1, m.getParameterTypes().length);
        assertEquals(B.class, m.getParameterTypes()[0]);
    }

    @Test
    public void testGetBooleanPrimitive() throws Exception {
        // TODO
    }
}
