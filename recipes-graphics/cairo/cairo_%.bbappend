# meta-raspberrypi appends "egl glesv2" via :append:rpi when vc4graphics is off,
# but cairo 1.18.4 (wrynose) dropped those PACKAGECONFIGs. Strip them; we
# build Qt with software rendering only and don't need GL/EGL in cairo.
PACKAGECONFIG:remove = "egl glesv2"
