package com.halfhp.fig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;
import org.robolectric.annotation.*;

import java.io.*;
import java.net.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class FigTest {

    class A {
        private int d = 0;
        private float f = 0;
        private float p = 0;
        private boolean aBooleanPrimitive;

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

        public float getF() {
            return f;
        }

        public void setF(float f) {
            this.f = f;
        }

        public float getP() {
            return p;
        }

        public void setP(float p) {
            this.p = p;
        }
    }

    class B {
        private A a = new A();

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    class C {
        private B b = new B();

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
    public void testConfigure() throws Exception {
        C c = new C();
        assertFalse(c.getB().getA().isaBooleanPrimitive());
        assertEquals(0, c.getB().getA().getD());

        // load xml config and verify:
        File f = getFileFromPath("c_config.xml");
        Fig.configure(RuntimeEnvironment.application, c, f);

        assertTrue(c.getB().getA().isaBooleanPrimitive());
        assertEquals(99, c.getB().getA().getD());
        assertEquals(2f, c.getB().getA().getF());
        assertEquals(6.6666665f, c.getB().getA().getP());
    }

    private File getFileFromPath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }
}
