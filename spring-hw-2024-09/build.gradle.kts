import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
	idea
	id("io.spring.dependency-management")
	id("org.springframework.boot") apply false
	id("com.diffplug.spotless")  apply false
}

idea {
	project {
		languageLevel = IdeaLanguageLevel(17)
	}
	module {
		isDownloadJavadoc = true
		isDownloadSources = true
	}
}

allprojects {
	group = "ru.nspk"

	repositories {
		mavenCentral()
	}

	val testcontainersBomVersion: String by project
	val findbugsJsr305: String by project

	apply(plugin = "io.spring.dependency-management")
	dependencyManagement {
		dependencies {
			imports {
				mavenBom(BOM_COORDINATES)
				mavenBom("org.testcontainers:testcontainers-bom:$testcontainersBomVersion")
			}
			dependency ("com.google.code.findbugs:jsr305:$findbugsJsr305")
		}
	}
	configurations.all {
		resolutionStrategy {
			failOnVersionConflict()
			force("com.google.code.findbugs:jsr305:3.0.2")
			force("com.google.guava:guava:32.1.2-jre")
			force("org.eclipse.platform:org.eclipse.osgi:3.21.0")
		}
	}
}

subprojects {
	plugins.apply(JavaPlugin::class.java)
	extensions.configure<JavaPluginExtension> {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
	configure<com.diffplug.gradle.spotless.SpotlessExtension> {
		java {
			palantirJavaFormat("2.38.0")
		}
	}

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
		options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing", "-Werror"))
	}

	tasks.withType<Test> {
		useJUnitPlatform()
		testLogging.showExceptions = true
		reports {
			junitXml.required.set(true)
			html.required.set(true)
		}
	}
}

tasks {
	val managedVersions by registering {
		doLast {
			project.extensions.getByType<DependencyManagementExtension>()
					.managedVersions
					.toSortedMap()
					.map { "${it.key}:${it.value}" }
					.forEach(::println)
		}
	}
}
