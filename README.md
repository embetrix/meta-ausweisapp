# meta-ausweisapp


Yocto/Openembedded layer for the German eID client AusweisApp:

https://www.ausweisapp.bund.de


## Build

This layer relies on OpenEmbedded/Yocto build system and depends on:

```
[OECORE]
URI: https://git.yoctoproject.org/git/poky.git
layers: meta
branch: same dedicated branch as meta-ausweisapp
```

```
[OE]
URI: https://github.com/openembedded/meta-openembedded.git
layers: meta-oe
branch: same dedicated branch as meta-ausweisapp
```

```
[QT6]
URI: git://code.qt.io/yocto/meta-qt6.git
layers: meta
branch: 6.9.3
```

It can be added to your layer(s) and enabling `ausweisapp` by adding:

```
IMAGE_INSTALL:append = " ausweisapp"
```

or built standalone using [kas-tool](https://github.com/siemens/kas):

```
KAS_MACHINE=qemux86-64 kas build kas-ausweisapp.yml
```

or using kas docker container:

```
KAS_MACHINE=qemux86-64 kas-container build kas-ausweisapp.yml
```

## Run in Qemu Emulator

```
KAS_MACHINE=qemux86-64 kas shell kas-ausweisapp.yml -c 'runqemu kvm serialstdio nographic qemuparams="-m 1024"'
```

## Tested MACHINE targets

*raspberrypi4-64* 

*stm32mp157c-dk2*

*stm32mp157f-dk2*

