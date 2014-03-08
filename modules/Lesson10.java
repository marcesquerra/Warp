package warp;

import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;

public class Lesson10 {
    private boolean done = false;
    private boolean fullscreen = false;
    private final String windowTitle = "NeHe's OpenGL Lesson 10 for LWJGL (Loading And Moving Through A 3D World)";
    private boolean f1 = false;
    private DisplayMode displayMode;


    private boolean render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  // Clear The Screen And The Depth Buffer
        GL11.glLoadIdentity();                                  // Reset The View

        float x_m, y_m, z_m, u_m, v_m;
        float xtrans = -xpos;
        float ztrans = -zpos;
        float ytrans = -walkbias-0.25f;
        float sceneroty = 360.0f - yrot;

        int numTriangles;

        GL11.glRotatef(lookupdown,1.0f,0,0);
        GL11.glRotatef(sceneroty,0,1.0f,0);

        GL11.glTranslatef(xtrans, ytrans, ztrans);

        numTriangles = sector1.numTriangles;

        // Process Each Triangle
        for (int loop_m = 0; loop_m < numTriangles; loop_m++)
        {
            GL11.glBegin(GL11.GL_TRIANGLES);
                GL11.glNormal3f( 0.0f, 0.0f, 1.0f);
                x_m = sector1.triangle[loop_m].vertex[0].x;
                y_m = sector1.triangle[loop_m].vertex[0].y;
                z_m = sector1.triangle[loop_m].vertex[0].z;
                u_m = sector1.triangle[loop_m].vertex[0].u;
                v_m = sector1.triangle[loop_m].vertex[0].v;
                GL11.glTexCoord2f(u_m,v_m); GL11.glVertex3f(x_m,y_m,z_m);

                x_m = sector1.triangle[loop_m].vertex[1].x;
                y_m = sector1.triangle[loop_m].vertex[1].y;
                z_m = sector1.triangle[loop_m].vertex[1].z;
                u_m = sector1.triangle[loop_m].vertex[1].u;
                v_m = sector1.triangle[loop_m].vertex[1].v;
                GL11.glTexCoord2f(u_m,v_m); GL11.glVertex3f(x_m,y_m,z_m);

                x_m = sector1.triangle[loop_m].vertex[2].x;
                y_m = sector1.triangle[loop_m].vertex[2].y;
                z_m = sector1.triangle[loop_m].vertex[2].z;
                u_m = sector1.triangle[loop_m].vertex[2].u;
                v_m = sector1.triangle[loop_m].vertex[2].v;
                GL11.glTexCoord2f(u_m,v_m); GL11.glVertex3f(x_m,y_m,z_m);
            GL11.glEnd();
        }
        return true;
    }
    private void createWindow() throws Exception {
        Display.setFullscreen(fullscreen);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640
                && d[i].getHeight() == 480
                && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle(windowTitle);
        Display.create();
    }
    private void init() throws Exception {
        createWindow();

        setupWorld();

        initGL();
    }

    private static void cleanup() {
        Display.destroy();
    }

}
