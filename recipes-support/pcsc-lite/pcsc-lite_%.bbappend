FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://pcscd-hardening.conf"

inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system pcscd"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system/pcscd.socket.d
    install -m 0644 ${UNPACKDIR}/pcscd-hardening.conf \
        ${D}${systemd_unitdir}/system/pcscd.socket.d/pcscd-hardening.conf
}

FILES:${PN} += "${systemd_unitdir}/system/pcscd.socket.d/"
