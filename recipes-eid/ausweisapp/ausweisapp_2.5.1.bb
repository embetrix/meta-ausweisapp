DESCRIPTION = "Der offizielle eID-Client des Bundes."
LICENSE = "EUPL-1.2"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=73c58ffb950091efba36039f56d8f3b0"

DEPENDS  = "qtdeclarative qttools-native doxygen-native"
DEPENDS += "qtbase qtscxml qtwebsockets qtconnectivity qtsvg qtlanguageserver opensc openssl llhttp"

RDEPENDS:${PN} += " cantarell-fonts qtsvg"

inherit qt6-cmake systemd

SRC_URI = "git://git@github.com/Governikus/AusweisApp.git;protocol=ssh;branch=community \
           file://0001-disable-host-tests.patch \
           file://0002-disable-man-page-install.patch \
           file://AusweisApp2.conf \
           file://AusweisApp2-sim.conf \
           file://ausweisapp.service \
           file://ausweisapp.env \
          " 

# TAG v2.5.1
SRCREV = "ec80d3ab326b5c1d33f3288014b0d73d090c1061"

EXTRA_OECMAKE += "-DBUILD_TESTING=OFF -DQT_DEBUG_FIND_PACKAGE=ON"

SYSTEMD_SERVICE:${PN} = "ausweisapp.service"
SYSTEMD_PACKAGES = "${PN}"

inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system ausweisapp"
USERADD_PARAM:${PN}  = "--system --no-create-home -g ausweisapp -s /bin/false ausweisapp"

do_install:append() {

	install -d ${D}${sysconfdir}/AusweisApp
	install -m 0644 ${UNPACKDIR}/AusweisApp2.conf ${D}${sysconfdir}/AusweisApp/
    install -m 0644 ${UNPACKDIR}/AusweisApp2-sim.conf ${D}${sysconfdir}/AusweisApp/
	install -m 0644 ${UNPACKDIR}/ausweisapp.env  ${D}${sysconfdir}/AusweisApp/

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${UNPACKDIR}/ausweisapp.service ${D}${systemd_unitdir}/system/

    install -d -m 0750 -o ausweisapp -g ausweisapp ${D}${localstatedir}/ausweisapp
}

FILES:${PN} += "${datadir} ${sysconfdir}/AusweisApp ${localstatedir}/ausweisapp"
