DESCRIPTION = "RPi Secure AusweisApp Demo Image"

COMPATIBLE_MACHINE = "^raspberrypi.*"

require recipes-core/images/rpi-secure-image-base.bb

IMAGE_INSTALL += "\
    ausweisapp \
    "
