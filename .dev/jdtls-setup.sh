#!/bin/bash

config_base_path=/usr/local/lib/jdtls
original_pwd=$(pwd)

if [ ! -d $config_base_path ]; then
  mkdir $config_base_path
fi

cd $config_base_path

if [ ! -d "plugins" ] || [ ! -f "plugins/org.eclipse.equinox.launcher_1.6.400.v20210924-0641.jar" ]; then
  rm -rf $config_base_path/plugins
  wget https://www.eclipse.org/downloads/download.php?file=/jdtls/milestones/1.9.0/jdt-language-server-1.9.0-202203031534.tar.gz
  tar -xzf jdt-language-server-1.9.0-202203031534.tar.gz
fi

echo "Configs for the jdtls LSP server applied to $config_base_path"

# Command to start the LSP server
# java \
#   -Declipse.application=org.eclipse.jdt.ls.core.id1 \
#   -Dosgi.bundles.defaultStartLevel=4 \
#   -Declipse.product=org.eclipse.jdt.ls.core.product \
#   -Dlog.level=ALL \
#   -Xmx1G \
#   --add-modules=ALL-SYSTEM \
#   --add-opens java.base/java.util=ALL-UNNAMED \
#   --add-opens java.base/java.lang=ALL-UNNAMED \
#   -jar ./plugins/org.eclipse.equinox.launcher_1.6.400.v20210924-0641.jar \
#   -configuration ./config_linux \
#   -data ../

exit 0
