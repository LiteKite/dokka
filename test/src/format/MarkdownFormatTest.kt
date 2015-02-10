package org.jetbrains.dokka.tests

import org.junit.Test
import org.jetbrains.dokka.*

public class MarkdownFormatTest {
    private val markdownService = MarkdownFormatService(InMemoryLocationService, KotlinLanguageService())

    Test fun emptyDescription() {
        verifyOutput("test/data/format/emptyDescription.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun classWithClassObject() {
        verifyOutput("test/data/format/classWithClassObject.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun annotations() {
        verifyOutput("test/data/format/annotations.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun annotationClass() {
        verifyOutput("test/data/format/annotationClass.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun annotationParams() {
        verifyOutput("test/data/format/annotationParams.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun extensions() {
        verifyOutput("test/data/format/extensions.kt", ".package.md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members)
        }
        verifyOutput("test/data/format/extensions.kt", ".class.md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun enumClass() {
        verifyOutput("test/data/format/enumClass.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
        verifyOutput("test/data/format/enumClass.kt", ".value.md") { model, output ->
            val enumClassNode = model.members.single().members[0]
            markdownService.appendNodes(tempLocation, output,
                    enumClassNode.members.filter { it.name == "LOCAL_CONTINUE_AND_BREAK" })
        }
    }

    Test fun varargsFunction() {
        verifyOutput("test/data/format/varargsFunction.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun overridingFunction() {
        verifyOutput("test/data/format/overridingFunction.kt", ".md") { model, output ->
            val classMembers = model.members.single().members.first { it.name == "D" }.members
            markdownService.appendNodes(tempLocation, output, classMembers.filter { it.name == "f" })
        }

    }

    Test fun propertyVar() {
        verifyOutput("test/data/format/propertyVar.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun functionWithDefaultParameter() {
        verifyOutput("test/data/format/functionWithDefaultParameter.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun accessor() {
        verifyOutput("test/data/format/accessor.kt", ".get.md") { model, output ->
            val propertyNode = model.members.single().members.first { it.name == "C" }.members.first { it.name == "x" }
            markdownService.appendNodes(tempLocation, output, listOf(propertyNode.members[0]))
        }
        verifyOutput("test/data/format/accessor.kt", ".set.md") { model, output ->
            val propertyNode = model.members.single().members.first { it.name == "C" }.members.first { it.name == "x" }
            markdownService.appendNodes(tempLocation, output, listOf(propertyNode.members[1]))
        }
    }

    Test fun paramTag() {
        verifyOutput("test/data/format/paramTag.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun throwsTag() {
        verifyOutput("test/data/format/throwsTag.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }

    Test fun typeParameterBounds() {
        verifyOutput("test/data/format/typeParameterBounds.kt", ".md") { model, output ->
            markdownService.appendNodes(tempLocation, output, model.members.single().members)
        }
    }
}
