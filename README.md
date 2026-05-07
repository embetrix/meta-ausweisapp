# meta-ausweisapp

Yocto/Openembedded layer for the German eID client AusweisApp:

https://www.ausweisapp.bund.de


## Build

Using [kas-tool](https://github.com/siemens/kas):

```
KAS_MACHINE=qemux86-64 kas build kas-ausweisapp.yml
```

or using kas docker container:

```
KAS_MACHINE=qemux86-64 kas-container build kas-ausweisapp.yml
```

### Raspberry Pi

`kas-ausweisapp-rpi.yml` is a fragment that includes `kas-ausweisapp.yml`
and overlays Raspberry Pi-specific settings (machine, distro, RPI config):

```
kas build kas-ausweisapp-rpi.yml
```

Combine with the signing-keys fragment for a signed build:

```
kas build kas-ausweisapp-rpi.yml:kas-signing-keys.yml
```

## Run in Qemu Emulator

```
KAS_MACHINE=qemux86-64 kas shell kas-ausweisapp.yml -c 'runqemu kvm serialstdio nographic qemuparams="-m 1024"'
```

## Tested MACHINE targets

* raspberrypi4-64

* raspberrypi5

* stm32mp157c-dk2

* stm32mp157f-dk2
