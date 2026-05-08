SUMMARY = "Port of http_parser to llparse"
DESCRIPTION = "llhttp is a port of http_parser to TypeScript. \
llparse is used to generate the output C source file."
HOMEPAGE = "https://github.com/nodejs/llhttp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9e4e583cb28b7e0c2ef8df4832706e96"

SRC_URI = "git://github.com/nodejs/llhttp.git;protocol=https;nobranch=1"
SRCREV = "751e5b44dc07a9932d244cb06210cfdcae951115"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS=ON -DLLHTTP_BUILD_SHARED_LIBS=ON -DLLHTTP_BUILD_STATIC_LIBS=OFF"

BBCLASSEXTEND = "native nativesdk"
