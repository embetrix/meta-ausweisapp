# Explicitly disable OverlayFS and default to bind mounts
AVOID_OVERLAYFS = "1"

# state and cache directories for AusweisApp2 on R+W data partition
VOLATILE_BINDS += "\
                /var/data/var/ausweisapp /var/ausweisapp\n"
