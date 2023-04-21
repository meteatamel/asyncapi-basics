# Account and Email Services

This sample shows AsyncAPI specs for a service that accepts CloudEvents in
binary and structured formats based on [Simulating CloudEvents with AsyncAPI and
Microcks](https://developers.redhat.com/articles/2021/06/02/simulating-cloudevents-asyncapi-and-microcks#)
blog post.

## Structured-mode

As a reminder, this is how a CloudEvent looks like in structured-mode:

```sh
curl localhost:8080 -v \
  -X POST \
  -H "Content-Type: application/cloudevents+json" \
  -d '{
        "specversion": "1.0",
        "type": "com.mycompany.myapp.myservice.myevent",
        "source": "myservice/mysource",
        "id": "1234-5678",
        "time": "2023-01-02T12:34:56.789Z",
        "subject": "my-important-subject",
        "datacontenttype": "application/json",
        "extensionattr1" : "value",
        "extensionattr2" : 5,
        "data": {
          "foo1": "bar1",
          "foo2": "bar2"
        }
      }'
```

You can see the AsyncAPI spec for Account Service accepting CloudEvents in
structured-mode in [account-service-ce-structured.yaml](account-service-ce-structured.yaml).

## Binary-mode

As a reminder, this is how a CloudEvent looks like in binary-mode:

```sh
curl localhost:8080 -v \
  -X POST \
  -H "Content-Type: application/json" \
  -H "ce-specversion: 1.0" \
  -H "ce-type: com.mycompany.myapp.myservice.myevent" \
  -H "ce-source: myservice/mysource" \
  -H "ce-id: 1234-5678" \
  -H "ce-time: 2023-01-02T12:34:56.789Z" \
  -H "ce-subject: my-important-subject" \
  -H "ce-extensionattr1: value" \
  -H "ce-extensionattr2: 5" \
  -d '{
        "foo1": "bar1",
        "foo2": "bar2"
      }'
```

You can see the AsyncAPI spec for Account Service accepting CloudEvents in
binary-mode in [account-service-ce-binary.yaml](account-service-ce-binary.yaml)
