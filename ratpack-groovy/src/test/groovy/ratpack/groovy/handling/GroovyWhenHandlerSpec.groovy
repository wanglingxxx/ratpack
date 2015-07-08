/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.groovy.handling

class GroovyWhenHandlerSpec extends BasicGroovyDslSpec {

  def "can use groovy truth in condition for true"() {
    when:
    handlers {
      when { 1 } {
        get { render "true" }
      }
    }

    then:
    text == "true"
  }

  def "can use groovy truth in condition for false"() {
    when:
    handlers {
      when { "" } {
        get { render "true" }
      }
      get { render "false" }
    }

    then:
    text == "false"
  }

  def "context is delegate for predicate"() {
    when:
    handlers {
      prefix(":foo?") {
        when { pathTokens.foo } {
          all { render "true" }
        }
        all { render "false" }
      }
    }

    then:
    getText("") == "false"
    getText("bar") == "true"
  }

}
