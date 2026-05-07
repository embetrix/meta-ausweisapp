FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-enable-framebuffer-rotation.patch"

PACKAGECONFIG:append = " no-opengl linuxfb optimize-size"
PACKAGECONFIG:remove = " gl gles2 eglfs"

QT_QPA_DEFAULT_PLATFORM = "linuxfb"
