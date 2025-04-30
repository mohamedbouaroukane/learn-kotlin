import com.jogamp.opengl.GL
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.awt.GLCanvas
import javax.swing.JFrame

fun main() {
    testOpenGLSupport()
}

fun testOpenGLSupport() {
    try {
        // طباعة كل الـ OpenGL Profiles المتوفرة
        println("Available GL Profiles: ${GLProfile.glAvailabilityToString()}")

        // طباعة الـ Default Profile الذي يستعمله JOGL
        println("Default GL Profile: ${GLProfile.getDefault().name}")

        val glCanvas = GLCanvas()
        glCanvas.addGLEventListener(object : GLEventListener {
            override fun init(drawable: GLAutoDrawable) {
                val gl = drawable.gl

                val glVersion = gl.glGetString(GL.GL_VERSION)
                val glVendor = gl.glGetString(GL.GL_VENDOR)
                val glRenderer = gl.glGetString(GL.GL_RENDERER)

                println("=====================================")
                println("OpenGL Version: $glVersion")
                println("OpenGL Vendor: $glVendor")
                println("OpenGL Renderer: $glRenderer")
                println("=====================================")
            }

            override fun display(drawable: GLAutoDrawable) {}
            override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {}
            override fun dispose(drawable: GLAutoDrawable) {}
        })

        val frame = JFrame("OpenGL Test")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(glCanvas)
        frame.setSize(400, 400)
        frame.isVisible = true
    } catch (e: Exception) {
        println("OpenGL not supported or configuration failed: ${e.message}")
        e.printStackTrace()
    }
}
