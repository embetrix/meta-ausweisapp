DESCRIPTION = "Der offizielle eID-Client des Bundes."
LICENSE = "EUPL-1.2"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=33583adf69edfcbf993ce6236ffe120a"

DEPENDS  = "qtdeclarative qttools-native doxygen-native"
DEPENDS += "qtbase qtscxml qtwebsockets qtconnectivity qtsvg qtlanguageserver opensc openssl"

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

# TAG v2.4.0
SRCREV = "52e0547f544da99019edfdb1bdd99a78393bf087"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DBUILD_TESTING=OFF -DQT_DEBUG_FIND_PACKAGE=ON"

SYSTEMD_SERVICE:${PN} = "ausweisapp.service"
SYSTEMD_PACKAGES = "${PN}"

inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system ausweisapp"
USERADD_PARAM:${PN}  = "--system --no-create-home -g ausweisapp -s /bin/false ausweisapp"

do_install:append() {

	install -d ${D}${sysconfdir}/AusweisApp
	install -m 0644 ${WORKDIR}/AusweisApp2.conf ${D}${sysconfdir}/AusweisApp/
    install -m 0644 ${WORKDIR}/AusweisApp2-sim.conf ${D}${sysconfdir}/AusweisApp/
	install -m 0644 ${WORKDIR}/ausweisapp.env  ${D}${sysconfdir}/AusweisApp/

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/ausweisapp.service ${D}${systemd_unitdir}/system/

    install -d -m 0750 -o ausweisapp -g ausweisapp ${D}${localstatedir}/ausweisapp
}

FILES:${PN} += "${datadir} ${sysconfdir}/AusweisApp ${localstatedir}/ausweisapp"
