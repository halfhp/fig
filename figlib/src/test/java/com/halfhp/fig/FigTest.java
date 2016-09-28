package com.halfhp.fig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.*;
import org.robolectric.annotation.*;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
//import com.halfhp.fig.test.R;

@RunWith(RobolectricTestRunner.class)
//@Config(resourceDir = "figLib/test/res",
//        manifest = "figLib/src/main/java/AndroidManifest.xml")
@Config(manifest=Config.NONE)
public class FigTest {

    class A {
        int d = 0;
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
    public void testConfigure() throws Exception {
        C c = new C();
        assertFalse(c.getB().getA().isaBooleanPrimitive());
        assertEquals(0, c.getB().getA().getD());

        // load xml config and verify:
        File f = getFileFromPath("c_config.xml");
        Fig.configure(RuntimeEnvironment.application, c, f);
        assertTrue(c.getB().getA().isaBooleanPrimitive());
        assertEquals(99, c.getB().getA().getD());
    }

    private File getFileFromPath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }
}
