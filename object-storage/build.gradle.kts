val ociSdkVersion: String by project

dependencies {
    implementation(project(":storage"))
    implementation(project(":common"))

    implementation("com.oracle.oci.sdk:oci-java-sdk-objectstorage:$ociSdkVersion")
    implementation("com.oracle.oci.sdk:oci-java-sdk-common-httpclient-jersey:$ociSdkVersion")
}