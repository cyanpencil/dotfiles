--------------------------------------------------------------------------
-- Copyright (c) 2007-2015 ETH Zurich.
-- All rights reserved.
--
-- This file is distributed under the terms in the attached LICENSE file.
-- If you do not find this file, copies can be found by writing to:
-- ETH Zurich D-INFK, Universitaetstasse 6, CH-8092 Zurich. Attn: Systems Group.
--
-- Arguments to major Hake targets
--
--------------------------------------------------------------------------

module Args where

import HakeTypes
import TreeDB

data Args = Args {
      buildFunction :: TreeDB -> String -> Args -> HRule,
      target :: String,
      driverType :: String,
      cFiles :: [String],
      generatedCFiles :: [String],
      assemblyFiles :: [String],
      mackerelDevices :: [String],
      addCFlags :: [String],
      omitCFlags :: [String],
      addIncludes :: [String],
      addGeneratedIncludes :: [String],
      omitIncludes :: [String],
      addLinkFlags :: [String],
      addLibraries :: [String],
      addModules :: [String],
      addGeneratedDependencies :: [String],
      architectures :: [String],
      installDirs :: InstallDirs
}

data InstallDirs = InstallDirs {
    bindir :: String,
    libdir :: String
}

defaultArgs = Args {
      buildFunction = defaultBuildFn,
      target = "",
      driverType = "",
      cFiles = [],
      generatedCFiles = [],
      assemblyFiles = [],
      mackerelDevices = [],
      addCFlags = [],
      omitCFlags = [],
      addIncludes = [],
      addGeneratedIncludes = [],
      omitIncludes = [],
      addLinkFlags = [],
      addLibraries = [],
      addModules = [],
      addGeneratedDependencies = [],
      architectures = allArchitectures,
      installDirs = InstallDirs {
            bindir = "/sbin",
            libdir = "/lib"
      }
}

allArchitectures = [ "armv7" ]
allArchitectureFamilies = [ "arm" ]

defaultBuildFn :: TreeDB -> String -> Args -> HRule
defaultBuildFn _ f _ =
    Error ("Bad use of default Args in " ++ f)

showArgs :: String -> Args -> String
showArgs prefix a =
    prefix ++ "Args:"
    ++ "\n  target:                " ++ (show $ target a)
    ++ "\n  cFiles:                " ++ (show $ cFiles a)
    ++ "\n  generatedCFiles:       " ++ (show $ generatedCFiles a)
    ++ "\n  assemblyFiles:         " ++ (show $ assemblyFiles a)
    ++ "\n  addCFlags:             " ++ (show $ addCFlags a)
    ++ "\n  omitCFlags:            " ++ (show $ omitCFlags a)
    ++ "\n  addIncludes:           " ++ (show $ addIncludes a)
    ++ "\n  omitIncludes:          " ++ (show $ omitIncludes a)
    ++ "\n  addLinkFlags:          " ++ (show $ addLinkFlags a)
    ++ "\n  addLibraries:          " ++ (show $ addLibraries a)
    ++ "\n  addModules:            " ++ (show $ addModules a)
    ++ "\n  addDeps:               " ++ (show $ addGeneratedDependencies a)
    ++ "\n  architectures:         " ++ (show $ architectures a)
    ++ "\n"
