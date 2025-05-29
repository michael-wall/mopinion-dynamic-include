**Introduction**
This is a simple OSGi module that dynamically includes the mopinion javascript in every page / screen of the Liferay system for an authenticated user.

**Usage**
- Add the following portal properties to the target environment
- custom.mopinion.id=......
- custom.mopinion.enabled=true
- Compile and deploy the OSGi module in a Liferay DXP 7.4 U92.

**Notes**
- Consider moving the mopinion properties to a custom System Settings Configuration to avoid needing a restart to change the values.
- This has been tested in a local Liferay DXP 7.4 U92 with JDK 11 for compile time and runtime.
- This is a ‘proof of concept’ that is being provided ‘as is’ without any support coverage or warranty.
- This should be tested in a non-production environment.
