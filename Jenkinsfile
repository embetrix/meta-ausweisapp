pipeline {
    agent { dockerfile true }

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'wrynose', selectedValue: 'DEFAULT', name: 'BRANCH', type: 'PT_BRANCH', description: 'branch to build'
        choice choices: ['qemux86-64', 'raspberrypi5', 'raspberrypi4-64', 'beaglebone-yocto'], description: 'select machine', name: 'MACHINE'
        choice choices: ['dev', 'prod'], description: 'select security profile', name: 'SECURITY_PROFILE'
        choice choices: ['no', 'yes'], description: 'clean workspace', name: 'CLEAN'
    }

    environment {
        KAS_CLONE_DEPTH = "1"
        SECURITY_PROFILE = "${params.SECURITY_PROFILE}"
    }

    stages {
 
        stage('Clean') {
            when {
                expression { params.CLEAN == 'yes' }
            }
            steps {
               sh "git clean -fdx"
            }
        }

        stage('Setup-RPI-Keys') {
            when {
                expression { params.MACHINE.contains('raspberrypi') }
            }
            steps {
                script {
                    def keyDir = "${env.WORKSPACE}/rpi-secure-keys"
                    def kasFragment = "kas-signing-keys.yml"
                    withCredentials([file(credentialsId: 'fd6cfa4d-679d-4d04-9e6a-74073de43385', variable: 'KEYS_TARBALL')]) {
                        sh "tar xzf \$KEYS_TARBALL -C ${env.WORKSPACE}"
                    }
                    sh "layers/meta-raspberrypi-secure/tools/genkey-helper.sh ${keyDir} ${kasFragment}"
                }
            }
        }

        stage('Build-Image') {
            steps {
                script {
                    def kasConfig = "kas-ausweisapp.yml"
                    if (params.MACHINE.contains('raspberrypi')) {
                        kasConfig = "kas-ausweisapp-rpi.yml:kas-signing-keys.yml"
                    }
                    sh "KAS_MACHINE=${params.MACHINE} kas build --force-checkout --update ${kasConfig}"
                    archiveArtifacts artifacts: "build/tmp/deploy/images/${params.MACHINE}/*-${params.MACHINE}.rootfs-*" ,
                                                 excludes: "**/*.ext3,**/*.ext4,**/*.rootfs.wic",
                                                 followSymlinks: true,
                                                 fingerprint: true,
                                                 onlyIfSuccessful: true
                }
            }
        }

    }
}
