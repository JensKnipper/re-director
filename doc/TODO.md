# TODO

## High Prio

- publish on GitHub
    - ci
        - built as docker container
            - live config, db as volume
        - publish to docker hub

## Medium Prio

- simplify css
    - utility classes for hr in table
- proper architecture - entity, dto, etc.
- different redirects, temporary etc.
- metrics, eg. last called
- create/update form validation
    - non null
    - non empty
    - source unique
    - target is an url
    - source is a domain - verify endings
- path, query parameter forwarding
- preserve request params when calling "redirect:/redirects"

## Low Prio

- table ordering
  - fixed to target name for now
  - dynamically
- if redirect inactive or no match, redirect temporarily to own domain, currently it looks like that this is then hosted under the target domain
- pagination
- table
    - odd coloring
    - show header next to values in mobile view
    - move styling into css file
- password protection