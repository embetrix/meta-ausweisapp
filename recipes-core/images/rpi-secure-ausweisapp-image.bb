DESCRIPTION = "AusweisApp Demo Image"

inherit core-image

# Pull in the RPi secure base only when building for a Raspberry Pi machine
require ${@bb.utils.contains('MACHINEOVERRIDES', 'rpi', 'recipes-core/images/rpi-secure-image-base.bb', '', d)}

IMAGE_INSTALL += "\
    ausweisapp \
    "
