# meta-raspberrypi appends ",swrast" to GALLIUMDRIVERS for rpi, but mesa
# 26.x (wrynose) removed that driver name. Software rendering is provided
# by "softpipe", which oe-core's mesa.inc adds unconditionally — strip the
# stale entry so meson accepts the option set.
python () {
    drivers = d.getVar('GALLIUMDRIVERS') or ''
    if 'swrast' in drivers:
        d.setVar('GALLIUMDRIVERS', drivers.replace(',swrast', ''))
}
